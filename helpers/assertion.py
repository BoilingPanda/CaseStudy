import pytest
from requests import Response

from .body_schema import Body


def assert_response_code(response: Response, status_code=200):

    assert response.status_code == status_code, f"Status code should be {status_code}"


def assert_pet_status(pet:Body, status):

    assert status.__contains__(pet.status), f"Pet status should be {status}"


def assert_response_is_empty(response: Response):
    assert len(response.json()) == 0, f"Response should be empty"


def catch(func, object:dict, handle=lambda e: e):
    try:
        return func(**object)
    except ValueError as e:
        pytest.fail(str(e))
