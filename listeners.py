import logging

from locust import events


class listeners():

    def __init__(self):
        self.stats = None

    @events.request.add_listener
    def my_request_handler(request_type, name, response_time, response_length, response,
                           context, exception, start_time, url, **kwargs):
        if exception:
            logging.error(f"Request to {name} failed with exception {exception}")
        else:
            logging.info(f"Successfully made a request to: {name}")


    @events.quitting.add_listener
    def _(environment, **kw):
        if environment.stats.total.fail_ratio > 0.01:
            logging.error("Test failed due to failure ratio > 1%")
            environment.process_exit_code = 1
        elif environment.stats.total.avg_response_time > 400:
            logging.error("Test failed due to average response time ratio > 300 ms")
            environment.process_exit_code = 1
        elif environment.stats.total.get_response_time_percentile(0.95) > 600:
            logging.error("Test failed due to 95th percentile response time > 800 ms")
            environment.process_exit_code = 1
        else:
            environment.process_exit_code = 0

