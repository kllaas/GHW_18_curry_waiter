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

package business.curry.thepiekie.space.business.domain.orders

///**
// * Load post by it's id from the repository
// * Notice that now only cache is in [PostsRepository]
// */
//class LoadPostByIdUseCase @Inject constructor(
//    private val postsRepository: PostsRepository
//) : MediatorUseCase<LoadPostsByIdParameters, LoadPostByIdResult>() {
//
//    override fun execute(parameters: LoadPostsByIdParameters) {
//        val postInRepository = postsRepository.loadPostById(parameters.postId)
//
//        DefaultScheduler.execute {
//            if (postInRepository != null) {
//                result.postValue(UseCaseResult.Success(LoadPostByIdResult(postInRepository)))
//            } else {
//                result.postValue(UseCaseResult.Error(Exception("There is no such post in repository")))
//            }
//        }
//    }
//}

data class LoadPostsByIdParameters(val postId: String)
data class LoadPostByIdResult(val post: Order)