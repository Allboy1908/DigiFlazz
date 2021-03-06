package id.indosw.digiflazz.api.processor

import android.app.Activity
import id.indosw.digiflazz.api.controller.TopUpRequestController
import id.indosw.digiflazz.api.sign.SignMaker.getSign

class RequestTopUp(val activity: Activity) {
    private var username: String? = null
    private var backendUrl: String? = null
    private var key: String? = null
    private var refId: String? = null
    private var sku: String? = null
    private var custNumber: String? = null
    private var msg: String? = null
    fun setBackendUrl(backendUrl: String?) {
        this.backendUrl = backendUrl
    }

    fun setUserName(username: String?) {
        this.username = username
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun setRefId(refId: String?) {
        this.refId = refId
    }

    fun setSKU(sku: String?) {
        this.sku = sku
    }

    fun setCustomerNumber(custNumber: String?) {
        this.custNumber = custNumber
    }

    fun setMessage(msg: String?) {
        this.msg = msg
    }

    fun startRequestTopUp(requestListener: RequestListener?) {
        val signature = getSign(username!!, key!!, refId!!)
        TopUpRequestController.instance!!.execute(this, backendUrl, username, key, sku, custNumber, refId, msg, signature, requestListener!!)
    }

    interface RequestListener {
        fun onResponse(response: String?)
        fun onErrorResponse(message: String?)
    }
}