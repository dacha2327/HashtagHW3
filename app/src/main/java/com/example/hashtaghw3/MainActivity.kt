package com.example.hashtaghw3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.example.hashtaghw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: TextAdapter
    private var list : ArrayList<String> = arrayListOf()
    private var replaceWords = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveText()
        hs()
        initAdapter()

    }

    private fun saveText() {
        binding.btnSave.setOnClickListener {
            searchHash()
            binding.etHash.text.clear()
        }
    }

    private fun searchHash() {
        val words = binding.etHash.text.split(" ")

        for (word in words) {
            if (word.startsWith("#")) {
                val newWord = word.replace(Regex("[-+.^:;?!#,]") , "")
                list.add(newWord)
            }
        }
    }

    private fun initAdapter(){
        adapter = TextAdapter(list , this::clickListener)
        binding.rvHash.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags : String) {
        binding.etHash.setText(binding.etHash.text.toString().replace(replaceWords, ""))
        binding.etHash.setText("${binding.etHash.text}#$hashTags ")
        binding.etHash.setSelection(binding.etHash.length())
    }

    private fun hs() {
        binding.etHash.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.rvHash.isVisible = true
            }
        }

        binding.etHash.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val words = text?.split(" ")

                if (words != null) {
                    for (word in words) {
                        replaceWords = word
                        binding.rvHash.isVisible = word.startsWith("#")
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}