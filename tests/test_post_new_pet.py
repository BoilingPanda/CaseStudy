from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code, assert_response_is_empty
from helpers.request_body import get_post_body

api_function = ApiFunction()


def test_valid(new_pet):
    input_body = get_post_body()
    output_body = new_pet.dict(by_alias=True)

    output_body.pop('id')

    assert all((input_body.get(k) == v for k, v in output_body.items()))


def test_invalid():
    responses = api_function.post_pet(data={})
    print(responses.status_code)
    assert_response_code(responses, status_code=200)



