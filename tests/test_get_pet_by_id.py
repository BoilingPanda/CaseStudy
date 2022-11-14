
from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code, catch
from helpers.body_schema import Body

api_functions = ApiFunction()


def test_valid(new_pet):

    response = api_functions.get_pet_by_id(id=new_pet.id_)
    assert_response_code(response)

    current_pet = catch(Body, response.json())

    assert current_pet.id_ == new_pet.id_, f'ID should be {new_pet.id_}'


def test_invalid():
    response = api_functions.get_pet_by_id(id=1111)
    assert_response_code(response, status_code=404)