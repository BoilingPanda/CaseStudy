from enum import Enum


class Status(str, Enum):
    available = "available"
    pending = "pending"
    sold = "sold"
    some_status = "geçersiz-statü"
