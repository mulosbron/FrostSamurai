package com.mulosbron.frostsamurai

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.ar.core.Config
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.math.Position
import io.github.sceneview.node.Node
import kotlin.random.Random

class ARFragment : Fragment() {

    private lateinit var sceneView: ArSceneView
    private lateinit var modelNode: ArModelNode
    private lateinit var modelNodeNonSymmetric: ArModelNode
    private lateinit var scoreTextView: TextView
    private lateinit var livesTextView: TextView
    private lateinit var gameOverTextView: TextView

    private var score = 0
    private var lives = 3

    private val handler = Handler(Looper.getMainLooper())
    private val random = Random(System.currentTimeMillis())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a_r, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sceneView = view.findViewById<ArSceneView>(R.id.sceneView).apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        scoreTextView = view.findViewById(R.id.scoreTextView)
        livesTextView = view.findViewById(R.id.livesTextView)
        gameOverTextView = view.findViewById(R.id.gameOverTextView)
        gameOverTextView.visibility = View.GONE

        updateScore()
        updateLives()

        setupModels()
        startAnimation()

        sceneView.onTap = { _, node, _ ->
            handleTap(node)
        }
    }

    private fun setupModels() {
        modelNode = ArModelNode(sceneView.engine).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/snowflake.glb",
                scaleToUnits = 2.0f,
                centerOrigin = Position(0f, 0f, 0f)
            )
            isVisible = false
        }

        modelNodeNonSymmetric = ArModelNode(sceneView.engine).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/tree_branch.glb",
                scaleToUnits = 1.5f,
                centerOrigin = Position(0f, 0f, 0f)
            )
            isVisible = false
        }

        sceneView.addChild(modelNode)
        sceneView.addChild(modelNodeNonSymmetric)
    }

    private fun startAnimation() {
        spawnObjects()
        handler.postDelayed(object : Runnable {
            override fun run() {
                moveObjects()
                handler.postDelayed(this, 50)
            }
        }, 50)
        handler.postDelayed(object : Runnable {
            override fun run() {
                respawnObjects()
                handler.postDelayed(this, 3000)
            }
        }, 3000)
    }

    private fun spawnObjects() {
        val randomValue = random.nextInt(100)
        if (randomValue < 70) {
            modelNode.isVisible = true
            modelNodeNonSymmetric.isVisible = false
            setPositionRandomly(modelNode)
        } else {
            modelNode.isVisible = false
            modelNodeNonSymmetric.isVisible = true
            setPositionRandomly(modelNodeNonSymmetric)
        }
    }

    private fun respawnObjects() {
        modelNode.isVisible = false
        modelNodeNonSymmetric.isVisible = false
        handler.postDelayed({
            spawnObjects()
        }, 500)
    }

    private fun moveObjects() {
        if (modelNode.isVisible) {
            moveNodeRandomly(modelNode)
        }
        if (modelNodeNonSymmetric.isVisible) {
            moveNodeRandomly(modelNodeNonSymmetric)
        }
    }

    private fun moveNodeRandomly(node: ArModelNode) {
        val deltaX = (random.nextFloat() - 0.5f) * 0.02f
        val deltaY = (random.nextFloat() - 0.5f) * 0.02f
        val deltaZ = (random.nextFloat() - 0.5f) * 0.02f
        val currentPosition = node.position
        node.position = Position(
            currentPosition.x + deltaX,
            currentPosition.y + deltaY,
            currentPosition.z + deltaZ
        )
    }

    private fun setPositionRandomly(node: ArModelNode) {
        val x = (random.nextFloat() - 0.5f) * 2f
        val y = (random.nextFloat() - 0.5f) * 2f
        val z = -random.nextFloat() * 2f - 1f
        node.position = Position(x, y, z)
    }

    @SuppressLint("SetTextI18n")
    private fun handleTap(node: Node?) {
        if (node == modelNode && modelNode.isVisible) {
            score += 10
            updateScore()
            modelNode.isVisible = false
        } else if (node == modelNodeNonSymmetric && modelNodeNonSymmetric.isVisible) {
            lives -= 1
            updateLives()
            modelNodeNonSymmetric.isVisible = false

            if (lives <= 0) {
                handler.removeCallbacksAndMessages(null)
                gameOverTextView.visibility = View.VISIBLE
                gameOverTextView.text = "Oyun Bitti\nSkorunuz: $score"
                scoreTextView.visibility = View.GONE
                livesTextView.visibility = View.GONE

                saveShurikenToSharedPref()
            }
        }
    }

    private fun updateScore() {
        scoreTextView.text = getString(R.string.score_text, score)
    }

    private fun updateLives() {
        livesTextView.text = getString(R.string.lives_text, lives)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
    }

    private fun saveShurikenToSharedPref() {
        val shurikenValue = score / 10
        val prefs = requireActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt("shuriken", shurikenValue)
        editor.apply()
    }
}
