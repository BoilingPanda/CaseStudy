from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code

api_function = ApiFunction()

def test_valid(new_pet):

    response = api_function.delete_pet_by_id(id=new_pet.id_)
    assert_response_code(response)

    response = api_function.get_pet_by_id(id=new_pet.id_)
    assert_response_code(response, 404)


def test_invalid():
    response = api_function.delete_pet_by_id(id="invalid")
    assert_response_code(response, 404)


def test_unknown_id():
    response = api_function.delete_pet_by_id(id=-13)
    assert_response_code(response, 404)

