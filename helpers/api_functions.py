from requests import session, Response

from .parameters import BASE_URL
from .status import Status


class ApiFunction:

    def __init__(self, host: str = BASE_URL):
        self.host = host
        self.session = session()
        self.headers = {
            "Content-Type": "application/json",
            "Accept": "*/*",
            "Connection": "keep-alive"
        }


    def _make_request(self, method:str, endpoint:str, **kwargs):
        return self.session.request(
            method,
            f'{self.host}{endpoint}',
            headers=kwargs.pop('headers', self.headers),
            **kwargs
        )

    def _get_request(self, endpoint:str, **kwargs) -> Response:
        return self._make_request('GET', endpoint, **kwargs)

    def _post_request(self, endpoint: str, **kwargs) -> Response:
        return self._make_request('POST', endpoint, **kwargs)

    def _put_request(self, endpoint: str, **kwargs) -> Response:
        return self._make_request('PUT', endpoint, **kwargs)

    def _delete_request(self, endpoint: str, **kwargs) -> Response:
        return self._make_request('DELETE', endpoint, **kwargs)

    def get_pet_by_id(self, id: int, **kwargs) -> Response:
        return self._get_request(f'/pet/{id}', **kwargs)

    def get_pet_by_status(self, status: list[Status], **kwargs) -> Response:
        params = {
            'status': ','.join(status)
        }
        return self._get_request('/pet/findByStatus', params=params, **kwargs)

    def post_pet_by_id(self, id: int, name: str, status: str, **kwargs) -> Response:
        headers = self.headers.copy()
        headers['Content-Type'] = 'application/x-www-form-urlencoded'
        data = {
            'name': name,
            'status': status,
        }

        return self._post_request(f'/pet/{id}', headers=headers, data=data, **kwargs)

    def post_pet(self, data:dict, **kwargs) -> Response:
        return self._post_request('/pet', json=data, **kwargs)

    def upload_image_by_pet_id(self, id: int, data: dict, files: dict, **kwargs) -> Response:
        return self._post_request(f'/pet/{id}/uploadImage', headers={}, data=data, files=files, **kwargs)

    def put_pet(self, data: dict, **kwargs) -> Response:
        return self._put_request('/pet', json=data, **kwargs)

    def delete_pet_by_id(self, id: int, **kwargs) -> Response:
        return self._delete_request(f'/pet/{id}', **kwargs)


