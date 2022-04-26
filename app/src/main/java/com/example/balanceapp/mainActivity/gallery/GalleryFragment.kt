package com.example.balanceapp.mainActivity.gallery

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.balanceapp.databinding.FragmentGalleryBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class GalleryFragment : Fragment() {

    private var lineChart: LineChart? = null
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var listOfItems: Array<String> = arrayOf("Poids", "IMC", "Graisse","Osseux","Muscle","eau")


        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinner : Spinner =binding.spinner
        val aa = context?.let { ArrayAdapter(it, R.layout.simple_spinner_item, listOfItems) }
        spinner.adapter =  aa

        drawLineChart()

        binding.OK.setOnClickListener {
           setData()
        }
        return root
    }

    private fun drawLineChart() {
            lineChart = binding.lineChart
            lineChart!!.setTouchEnabled(true)
            lineChart!!.description.textSize = 12f
            lineChart!!.description.isEnabled = false
            val xAxis: XAxis = lineChart!!.xAxis
            xAxis.valueFormatter = object : IAxisValueFormatter {
                private val mFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.FRANCE)
                override fun getFormattedValue(value: Float, axis: AxisBase): String {
                    val millis = TimeUnit.SECONDS.toMillis(value.toLong())
                    return mFormat.format(Date(millis))
                }
            }
            xAxis.textSize = 10F
            setData()
            // draw points over time
            lineChart!!.animateX(1000)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.isGranularityEnabled = false
            xAxis.labelRotationAngle = -90.0f

            lineChart!!.axisLeft.isEnabled = true
            lineChart!!.invalidate()

    }

    private fun setData(){
        var lineEntries: List<Entry> = ArrayList()
        when (binding.spinner.selectedItem.toString()) {
            "Poids" -> lineEntries=getDataPOIDS()
            "IMC" -> lineEntries=getDataIMC()
            "Graisse" -> lineEntries=getDataGRAISSE()
            "Osseux" -> lineEntries=getDataOSSEUX()
            "Muscle" -> lineEntries=getDataMUSCULE()
            "eau" -> lineEntries=getDataEAU()

        }
        val lineDataSet = LineDataSet(lineEntries, "")
        lineDataSet.form = Legend.LegendForm.NONE
        lineDataSet.valueTextSize = 14f
        lineDataSet.isHighlightEnabled = true
        lineDataSet.lineWidth = 2f
        lineDataSet.color = Color.RED
        lineDataSet.setCircleColor(Color.RED)
        lineDataSet.circleRadius = 6f
        lineDataSet.setDrawFilled(true)
        lineDataSet.setDrawCircles(true)
        lineDataSet.mode = LineDataSet.Mode.LINEAR
        val lineData = LineData(lineDataSet)
        lineChart!!.data = lineData
        lineChart!!.invalidate()
    }

    private fun getDataPOIDS(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(70.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(75.0).toFloat()))
        return lineEntries
    }

    private fun getDataIMC(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(20.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(21.1).toFloat()))
        return lineEntries
    }

    private fun getDataGRAISSE(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(12.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(20.0).toFloat()))
        return lineEntries
    }
    private fun getDataOSSEUX(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(2.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(2.0).toFloat()))
        return lineEntries
    }
    private fun getDataMUSCULE(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(40.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(42.0).toFloat()))
        return lineEntries
    }
    private fun getDataEAU(): List<Entry> {
        val lineEntries: ArrayList<Entry> = ArrayList()
        lineEntries.add(Entry(1648219477.toFloat(),(35.0).toFloat()))
        lineEntries.add(Entry(1648319477.toFloat(),(34.0).toFloat()))
        return lineEntries
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}