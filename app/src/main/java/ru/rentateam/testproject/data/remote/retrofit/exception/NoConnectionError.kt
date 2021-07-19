package ru.rentateam.testproject.data.remote.retrofit.exception

import java.lang.RuntimeException

class NoConnectionError: RuntimeException("No active connection to the internet")