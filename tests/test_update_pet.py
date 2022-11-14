from helpers import status
from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code, catch, assert_response_is_empty
from helpers.body_schema import Body
from helpers.request_body import get_put_body
from helpers.status import Status

api_function = ApiFunction()


def test_valid(new_pet):

    put_pet_request = get_put_body(new_pet.id_)

    response = api_function.put_pet(data=put_pet_request)
    assert_response_code(response)

    current_pet = catch(Body, response.json())

    assert all(put_pet_request.get(k) == v for k, v in current_pet.dict(by_alias=True).items())

def test_invalid():
    data = {
        "id": "invalid"
    }

    response = api_function.put_pet(data=data)
    assert_response_code(response, 500)




def test_unknown_id():
    data = {
        "id": 556547
    }

    response = api_function.put_pet(data=data)
    assert_response_code(response, 200)



def test_invalid_update(new_pet):

    data = {
        "id": new_pet.id_,
        "status": Status.some_status
    }

    response = api_function.put_pet(data=data)
    assert_response_code(response, 200)

