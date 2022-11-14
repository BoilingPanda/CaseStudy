import pytest

from helpers.api_functions import ApiFunction
from helpers.assertion import assert_response_code
from helpers.request_body import get_post_body
from helpers.body_schema import Body
from helpers.status import Status

api_function = ApiFunction()


@pytest.fixture
def new_pet():

    response = api_function.post_pet(get_post_body())
    assert_response_code(response)

    try:
        current_pet = Body(**response.json())
    except ValueError as err:
        current_pet = None
        pytest.fail(str(err))

    yield current_pet

    api_function.delete_pet_by_id(id=current_pet.id_)


@pytest.fixture
def create_post_request_data(new_pet):
    response = api_function.get_pet_by_id(id=new_pet.id_)
    assert_response_code(response)

    try:
        current_pet = Body(**response.json())
    except ValueError as err:
        current_pet = None
        pytest.fail(str(err))

    yield {
        'id_': new_pet.id_,
        'name': 'doggie',
        'status': Status.available if current_pet != Status.available else Status.pending
    }

    response = api_function.post_pet_by_id(id=new_pet.id_, name=current_pet.name, status=current_pet.status )
    assert_response_code(response)
