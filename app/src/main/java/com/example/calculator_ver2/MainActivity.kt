package com.example.calculator_ver2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.lang.NumberFormatException
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private val expressionTextView : TextView by lazy{
        findViewById<TextView>(R.id.expressionTextView)
    }
    private val resultTextView: TextView by lazy{
        findViewById<TextView>(R.id.resultTextView)
    }

    private var isOperator = false
    private var hasOperator = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun buttonClicked  (v: View){
        when(v.id){
            R.id.button0 -> numberButtonClicked("0")
            R.id.button1 -> numberButtonClicked("1")
            R.id.button2-> numberButtonClicked("2")
            R.id.button3 -> numberButtonClicked("3")
            R.id.button4 -> numberButtonClicked("4")
            R.id.button5 -> numberButtonClicked("5")
            R.id.button6 -> numberButtonClicked("6")
            R.id.button7 -> numberButtonClicked("7")
            R.id.button8 -> numberButtonClicked("8")
            R.id.button9 -> numberButtonClicked("9")
            R.id.buttonPlus -> operatorButtonClicked("+")
            R.id.buttonMinus -> operatorButtonClicked("-")
            R.id.buttonMulti -> operatorButtonClicked("*")
            R.id.buttonDivider -> operatorButtonClicked("/")
            R.id.buttonModulo -> operatorButtonClicked("%")
        }
    }
    private fun numberButtonClicked(number:String){

        if(isOperator){
            expressionTextView.append(" ")
        }

        isOperator = false

        val expressionText = expressionTextView.text.split("")
        if(expressionText.isNotEmpty() && expressionText.last().length>=15){
            Toast.makeText(this,"15자리까지만 사용할 수 있습니다", Toast.LENGTH_SHORT);
            return
        } else if(expressionText.last().isEmpty() && number == "0"){
            Toast.makeText(this,"0은 제일 앞에 올 수 없습니다",Toast.LENGTH_SHORT)
            return
        }

        expressionTextView.append(number)
        // TODO resultTextView 실시간으로 계산 결과를 넣어야 하는 기능

    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun operatorButtonClicked(operator:String){
        if(expressionTextView.text.isEmpty()){
            return
        }
        when{
            isOperator -> {
                val text = expressionTextView.text.toString()
                expressionTextView.text = text.dropLast(1) + operator
            }
            hasOperator -> {
                Toast.makeText(this,"연산자는 한 번만 사용할 수 있습니다",Toast.LENGTH_SHORT)
                return
            }
            else -> {
                expressionTextView.append(" $operator")
            }
        }
        val ssb = SpannableStringBuilder(expressionTextView.text)
        ssb.setSpan(
            ForegroundColorSpan(getColor((R.color.green))),
            expressionTextView.text.length -1,
             expressionTextView.text.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        expressionTextView.text = ssb
        isOperator = true
        hasOperator = true
    }
    fun resultButtonClicked(v: View){

    }
    private fun calculateExpression():String {
        val expressionTexts = expressionTextView.text.split("")

        if(hasOperator.not()|| expressionTexts.size !=3){
            return ""
        } else if(expressionTexts[0].isNumber())
    }


    fun clearButtonClicked(v: View){
    }

    fun historyButtonClicked(v: View){

    }
}

fun String.isNumber():Boolean{
    return try{
        this.toBigInteger()
        true
    }catch (e:NumberFormatException){
        false
    }
}
