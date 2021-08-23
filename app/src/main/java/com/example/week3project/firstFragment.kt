package com.example.week3project

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


/**
 * A simple [Fragment] subclass.
 * Use the [.newInstance] factory method to
 * create an instance of this fragment.
 */
class firstFragment : Fragment() {
    private lateinit var Idname : EditText
    private lateinit var Idemail : EditText
    private lateinit var Submitbtn : Button
    private lateinit var Groupbtn : RadioGroup
    private lateinit var Malebtn : RadioButton
    private lateinit var Femalebtn : RadioButton
    private lateinit var Checkbtn: CheckBox
    private  lateinit var Tvorder: TextView
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_first, container, false)

        val Idname = v.findViewById<EditText>(R.id.idname)
        val Idemail = v.findViewById<EditText>(R.id.idemail)
        val Submitbtn = v.findViewById<Button>(R.id.submitbtn)
        val Groupbtn = v.findViewById<RadioGroup>(R.id.groupbtn)
        val Checkbtn = v.findViewById<CheckBox>(R.id.checkbtn)

        val Btn = v.findViewById<Button>(R.id.btn_timePicker)
        val dateTime = v.findViewById<TextView>(R.id.datetime)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        Btn.setOnClickListener {

            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                dateTime.text = "" + mYear + "/" + mMonth + "/" + mDay
            },
                year, month, day)
            dpd.show()
        }


        Submitbtn.setOnClickListener {


            val name = Idname.text.toString().trim()
            val email = Idemail.text.toString().trim()
            val isSelected = Groupbtn.checkedRadioButtonId
            val gender = v.findViewById<RadioButton>(isSelected)
            val orderString = "${gender.text}"
            val bundle = Bundle()
            bundle.putString("Name",name)
            bundle.putString("Email",email)
            bundle.putString("Gender",orderString)




            if (name.isEmpty()) {
                Toast.makeText(activity, "please enter name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (email.matches(emailPattern.toRegex())) {
            } else {
                Toast.makeText(activity, "please enter valid Email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (isSelected == -1) {
                Toast.makeText(activity, "please select gender", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!Checkbtn.isChecked) {
                Toast.makeText(activity, "please check T&C", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val SecondFragment = secondFragment()
            SecondFragment.arguments = bundle
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainlayout, SecondFragment)
            transaction.commit()

        }


        return v
    }

}

