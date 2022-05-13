fun main() {

    // Crea todas las clases/funciones necesarias para realizar este ejercicio sin modificar en nada Main.
    val lista = listOf(Gato(2.0), Perro(20.0), Persona(89.0),Gato(3.0), Perro(15.0), Persona(79.0),Gato(3.0), Perro(25.0), Persona(99.0))

    // Haz un ejercicio como output tenga:
    // El peso medio de los gatos es:
    println("El peso medio de los gatos es ${calcularPesoMedioGatos(lista)}")
    // El peso medio de los perros es:
    println("El peso medio de los perros es ${calcularPesoMedioPerros(lista)}")
    // El peso medio de las personas es:
    println("El peso medio de las personas es ${calcularPesoMedioPersonas(lista)}")

    // Utiliza Herencia para Calcular desviación respecto a su peso ideal.
    // Peso ideal del perro = 22
    // Peso ideal del gato = 2.2
    // Peso ideal de la persona = 85
    lista.forEach {
        // Para el gato 1, el resutlado es -0,2. Para el perro 1 - 2.0, para la persona 1 es + 4.
        println("Este animal tiene un peso de ${it.calcularDesviacionDelPesoIdeal()} sobre lo esprado")
    }

}

fun calcularPesoMedioPersonas(lista: List<Any>): Double {
    var contador=0
    var PesoMedio=0.0
    lista.forEach(){
        when(it) {
            is Persona -> {
                contador++
                PesoMedio+=it.d
            }
        }
    }
    return PesoMedio/contador
}

fun calcularPesoMedioPerros(lista: List<Any>): Double {
    var contador=0
    var PesoMedio=0.0
    lista.forEach(){
        when(it) {
            is Perro -> {
                contador++
                PesoMedio+=it.d
            }
        }
    }
    return PesoMedio/contador
}

fun calcularPesoMedioGatos(lista: List<Any>): Double {
    var contador=0
    var PesoMedio=0.0
    lista.forEach(){
        when(it) {
            is Gato -> {
                contador++
                PesoMedio+=it.d
            }
        }
    }
    return PesoMedio/contador
}

open class Persona(override var d: Double):Animal() {
    override var pesoIdeal=85.0
}

open class Perro(override var d: Double):Animal() {
    override var pesoIdeal=22.0
}

open class Gato(override var d: Double):Animal() {
    override var pesoIdeal=2.2
}
abstract class Animal(){
    fun calcularDesviacionDelPesoIdeal(): Double{

        return d-pesoIdeal
    }

    abstract val d: Double
    open var pesoIdeal: Double=0.0
}
