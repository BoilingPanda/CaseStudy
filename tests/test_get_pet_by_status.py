from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code, assert_pet_status, assert_response_is_empty, catch
from helpers.body_schema import Body
from helpers.status import Status

api_functions = ApiFunction()


def test_valid():
    status = Status.sold

    response = api_functions.get_pet_by_status(status=[status])
    assert_response_code(response)

    pets = [catch(Body, pet) for pet in response.json()]

    for pet in pets:

        assert_pet_status(pet, status)


def test_invalid():
    status = Status.some_status

    response = api_functions.get_pet_by_status(status=[status])
    assert_response_code(response, 200)


def test_valid_with_more_than_one_status():
    status = [Status.available, Status.sold]

    response = api_functions.get_pet_by_status(status=status)
    assert_response_code(response)

    pets = [catch(Body, pet) for pet in response.json()]

    for pet in pets:
        assert_pet_status(pet, status)

