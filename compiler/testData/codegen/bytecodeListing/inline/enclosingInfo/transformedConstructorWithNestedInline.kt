// FILE: 1.kt

package test

interface Z {
    fun a(): String
}

inline fun test(crossinline z: () -> String) =
    object : Z {
        val p = z()

        override fun a() = p
    }

inline fun <T> call(crossinline z: () -> T) = z()

// FILE: 2.kt

import test.*

fun box() {
    // This captured parameter would be added to object constructor
    val captured = "OK"
    var z: Any = "fail"
    val res = test {
        call {
            z = {
                captured
            }
        }
        (z as Function0<String>)()
    }
}
