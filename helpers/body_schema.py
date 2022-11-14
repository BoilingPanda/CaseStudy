from typing import Optional
from pydantic import BaseModel, StrictInt, Field, constr, StrictStr
from .status import Status


class Category(BaseModel):
    id_ : Optional[StrictInt] = Field(alias='id')
    name: Optional[StrictStr]


class Tag(BaseModel):
    id_: Optional[StrictInt] = Field(alias='id')
    name: Optional[StrictStr]


class Body(BaseModel):
    id_: StrictInt = Field(alias='id')
    name: Optional[StrictStr]
    photoUrls: list[str]
    category: Optional[Category]
    tags: Optional[list[Tag]]
    status: Optional[Status]