from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code, catch
from helpers.body_schema import Body

api_function = ApiFunction()


def test_valid(create_post_request_data):
    id_,name, status = create_post_request_data["id_"], create_post_request_data["name"], create_post_request_data["status"]

    response = api_function.post_pet_by_id(id=id_, name=name, status=status)
    assert_response_code(response)

    response = api_function.get_pet_by_id(id=id_)
    assert_response_code(response)

    current_pet = catch(Body, response.json())

    assert current_pet.name == name, f"Name should be changed to {name}"
    assert current_pet.status == status, f"Status should be changed to {status}"


def test_invalid():
    response = api_function.post_pet_by_id(id="invalid", name="", status="")
    assert_response_code(response, 404)


def test_unknown_id():
    response = api_function.post_pet_by_id(id=-1, name="", status="")
    assert_response_code(response, 404)

