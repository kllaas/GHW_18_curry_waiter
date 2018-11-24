/*
 * MIT License
 *
 * Copyright (c) 2018 Artem Vasylenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package business.curry.thepiekie.space.business.data.remote

import business.curry.thepiekie.space.business.data.model.LoginResponse
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.domain.login.LoginParams
import io.reactivex.Observable
import retrofit2.http.*
import javax.inject.Singleton

/**
 * Responsible for fetching post data from the Reddit API
 */
@Singleton
interface CurryApi {

    @POST(LOGIN)
    fun login(@Body body: LoginParams): Observable<LoginResponse>

    @GET(GET_ORDER_BY_ID)
    fun getOrderById(@Query("id") id: String): Observable<OrderPlace>

    @GET(GET_ORDERS)
    fun getOrders(): Observable<List<OrderPlace>>

    companion object {

        const val BASE_URL = "https://api.thepiekie.space/curry/v1/"

        private const val LOGIN = "login/"
        private const val GET_ORDERS = "orders/"
        private const val GET_ORDER_BY_ID = "order/{id}"

    }
}