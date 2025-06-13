package zed.rainxch.pljuneminichallenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TestViewmodel : ViewModel() {

    init {
        viewModelScope.launch {
            coroutineTest()
        }
    }

    val myContext = Dispatchers.IO + Job() + CoroutineExceptionHandler(
        { _, _ ->

        },
    )

    fun start() {
        println("start")
    }

    suspend fun coroutineTest() = runBlocking {
        launch {
            println("start of our first coroutine")
            repeat(5) {
                println("its $it")
                delay(2000)
            }
            println("end of our first coroutine")
        }

        delay(1000)
        println("outer delay finished (1 second)")

        // 34
        // its 1
//        43
//        2,3,4,5
//        39

    }
}