from random import randint
from .parameters import pet_photos
from .status import Status


def get_post_body():
    """
    Returns the post body as a string.
    """

    return {
          "category": {
            "id": 10,
            "name": "Cat"
          },
          "name": "doggie",
          "photoUrls": [
            pet_photos[0]
          ],
          "tags": [
            {
              "id": 11,
              "name": "kitten"
            }
          ],
          "status": Status.available
        }


def get_put_body(id):

    return {
          "id": id,
          "category": {
            "id": 16,
            "name": "Dog"
          },
          "name": "doggie",
          "photoUrls": [
            pet_photos[randint(0, len(pet_photos))]
          ],
          "tags": [
            {
              "id": 12,
              "name": "puppy"
            }
          ],
          "status": Status.available
        }

