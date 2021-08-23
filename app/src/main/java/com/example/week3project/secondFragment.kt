package com.example.week3project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3project.model.UserData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.malkinfo.editingrecyclerview.view.UserAdpter


class secondFragment : Fragment() {
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdpter
    private lateinit var Submitbtn : Button
    private lateinit var Groupbtn : RadioGroup
//    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())

        val v = inflater.inflate(R.layout.add_list, null)

        val userName = v.findViewById<EditText>(R.id.idname)
        val userEmail = v.findViewById<EditText>(R.id.idemail)
        val Groupbtn = v.findViewById<RadioGroup>(R.id.groupbtn)
        val addDialog = AlertDialog.Builder(requireContext())


       addDialog.setView(v)
        addDialog.setPositiveButton("ok") { dialog, _ ->

            val names = userName.text.toString()
            val emails = userEmail.text.toString()
            val isSelected = Groupbtn.checkedRadioButtonId
            val gender = v.findViewById<RadioButton>(isSelected)
            val orderString = "${gender.text}"

            userList.add(UserData("Name : $names", "Email : $emails","Gender : $orderString"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(activity, "adding user  Successsfull", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("cancel") { dilog, _ ->
            dilog.dismiss()
            Toast.makeText(activity, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val Mname : TextView = view.findViewById(R.id.mTitle)
        val Memail : TextView = view.findViewById(R.id.mSubTitle)
        val Mgender : TextView = view.findViewById(R.id.mgender)

        val args = this.arguments
        val Username = args?.get("Name")
        val Userid = args?.get("Email")
        val gender = args?.get("Gender")
        Mname.text = "Name:" + Username.toString()
        Memail.text = "Email :" + Userid.toString()
        Mgender.text = "Gender :" + gender.toString()

        userList = ArrayList()

        addsBtn = view.findViewById(R.id.addingbtn)
        recv = view.findViewById(R.id.mRecycler)
        userAdapter = UserAdpter(context as MainActivity, userList)

        //setRecycler view adpter

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = userAdapter
        addsBtn.setOnClickListener { addInfo() }
        return view

    }

}