package com.example.daohang.ui.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daohang.ModelApiService
import com.example.daohang.R
import com.example.daohang.databinding.FragmentGalleryBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException



class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var imageView: ImageView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val galleryViewModel =
//            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val generateButton: Button = requireActivity().findViewById(R.id.but10)
        val editText: EditText = requireActivity().findViewById(R.id.editText)
        imageView = view.findViewById(R.id.image)

        generateButton.setOnClickListener {
            val inputText = editText.text.toString()
            generateImageFromText(inputText)
        }
    }

    private fun generateImageFromText(text: String) {
        Log.d("MyApp", "Generating image from text: $text") // 调试语句

        GlobalScope.launch(Dispatchers.IO) {
            val imageBytes = ModelApiService.generateImageFromText(text)

            val bitmap = decodeBitmapFromByteArray(imageBytes)
            showImage(bitmap)
            saveImage(bitmap)
        }
    }

    private suspend fun decodeBitmapFromByteArray(imageBytes: ByteArray?): Bitmap? {
        return withContext(Dispatchers.Default) {
            if (imageBytes != null && imageBytes.isNotEmpty()) {
                BitmapFactory.decodeStream(ByteArrayInputStream(imageBytes))
            } else {
                null
            }
        }
    }

    private suspend fun showImage(bitmap: Bitmap?) {
        Log.d("MyApp", "Showing image: $bitmap") // 调试语句

        withContext(Dispatchers.Main) {
            imageView.setImageBitmap(bitmap)
        }
    }

    private suspend fun saveImage(bitmap: Bitmap?) {
        // 创建一个唯一的文件名，例如基于时间戳
        val timestamp = System.currentTimeMillis()
        val fileName = "image_$timestamp.png"

        // 设置保存文件的路径和文件名
        val imageFile = File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName) // 更改为你希望保存的文件路径和文件名

        withContext(Dispatchers.IO) {
            // 创建一个文件输出流
            try {
                val outputStream = FileOutputStream(imageFile)
                // 将 Bitmap 压缩为 PNG 格式，并写入文件输出流
                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

                // 关闭输出流
                outputStream.close()

                println("保存成功")
            } catch (e: IOException) {
                e.printStackTrace()

                // 在此处你可以添加一些保存失败的处理逻辑
                println("保存失败")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//    fun getExternalFilesDir(type: String?): File? {
//        throw RuntimeException("Stub!")
//    }
}