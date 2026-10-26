package com.example.eva1programacion2

import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.eva1programacion2.modelo.CuentaMesa
import com.example.eva1programacion2.modelo.ItemMenu
import com.example.eva1programacion2.modelo.ItemMesa
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Instancias del modelo de datos
    private val pastelDeChoclo = ItemMenu(nombre = "Pastel de Choclo", precio = 12000)
    private val cazuela = ItemMenu(nombre = "Cazuela", precio = 10000)

    private val itemPastel = ItemMesa(itemMenu = pastelDeChoclo, cantidad = 0)
    private val itemCazuela = ItemMesa(itemMenu = cazuela, cantidad = 0)

    private val cuentaMesa = CuentaMesa(
        items = mutableListOf(itemPastel, itemCazuela),
        aceptaPropina = true
    )

    // Declaración de Vistas de la Interfaz
    private lateinit var etCantidadPastel: EditText
    private lateinit var tvSubtotalPastel: TextView
    private lateinit var etCantidadCazuela: EditText
    private lateinit var tvSubtotalCazuela: TextView
    private lateinit var switchPropina: Switch
    private lateinit var tvSubtotalComida: TextView
    private lateinit var tvMontoPropina: TextView
    private lateinit var tvTotalFinal: TextView

    // Formateador de moneda para pesos chilenos (CLP)
    private val formatoMoneda: NumberFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuración de Insets para respetar las barras del sistema (status bar / nav bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialización de componentes XML
        initViews()

        // Configuración de escuchadores de eventos (TextWatcher y Switch)
        setupEventListeners()

        // Estado inicial de la pantalla
        actualizarPantalla()
    }

    /**
     * Vincula las variables de Kotlin con las vistas correspondientes definidas en activity_main.xml.
     */
    private fun initViews() {
        etCantidadPastel = findViewById(R.id.etCantidadPastel)
        tvSubtotalPastel = findViewById(R.id.tvSubtotalPastel)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        tvSubtotalCazuela = findViewById(R.id.tvSubtotalCazuela)
        switchPropina = findViewById(R.id.switchPropina)
        tvSubtotalComida = findViewById(R.id.tvSubtotalComida)
        tvMontoPropina = findViewById(R.id.tvMontoPropina)
        tvTotalFinal = findViewById(R.id.tvTotalFinal)
    }

    /**
     * Configura los controladores de eventos para los EditText y el Switch.
     */
    private fun setupEventListeners() {
        // Evento TextChanged para el campo de cantidad de Pastel de Choclo
        etCantidadPastel.doAfterTextChanged { text ->
            val cantidad = text.toString().toIntOrNull() ?: 0
            itemPastel.cantidad = cantidad
            actualizarPantalla()
        }

        // Evento TextChanged para el campo de cantidad de Cazuela
        etCantidadCazuela.doAfterTextChanged { text ->
            val cantidad = text.toString().toIntOrNull() ?: 0
            itemCazuela.cantidad = cantidad
            actualizarPantalla()
        }

        // Evento CheckedChange para el Switch de propina opcional (10%)
        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarPantalla()
        }
    }

    /**
     * Actualiza dinámicamente los valores en pantalla según los cálculos del modelo de datos.
     */
    private fun actualizarPantalla() {
        tvSubtotalPastel.text = formatearMoneda(itemPastel.calcularSubtotal())
        tvSubtotalCazuela.text = formatearMoneda(itemCazuela.calcularSubtotal())
        tvSubtotalComida.text = formatearMoneda(cuentaMesa.calcularSubtotalSinPropina())
        tvMontoPropina.text = formatearMoneda(cuentaMesa.calcularPropina())
        tvTotalFinal.text = formatearMoneda(cuentaMesa.calcularTotalConPropina())
    }

    /**
     * Convierte un valor entero a un String en formato moneda chilena ($12.000).
     */
    private fun formatearMoneda(monto: Int): String {
        return formatoMoneda.format(monto)
    }
}
