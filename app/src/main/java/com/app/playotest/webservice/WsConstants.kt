package com.app.playotest.webservice

/**
 * Common webservice constants.
 */

object WsConstants {
    /**
     * Webservice url
     */
    const val WS_BASE_URL = "https://hn.algolia.com/api/v1/"

    const val WS_CONNECTION_TIMEOUT = 60
    const val WS_READ_TIMEOUT = 60

    const val DEVICE_OS_ANDROID = "ANDROID"


    /**
     * Webservice parameters
     */
    const val PARAM_QUERY = "query"
    const val PARAM_PAGE = "page"

    /**
     * Webservice method
     */
    const val METHOD_SEARCH = "search"


}
