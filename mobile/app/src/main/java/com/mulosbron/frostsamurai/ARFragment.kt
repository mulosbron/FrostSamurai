package com.mulosbron.frostsamurai

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.google.ar.core.Config
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import io.github.sceneview.ar.ArSceneView
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position
import io.github.sceneview.node.VideoNode

class ARFragment : Fragment() {

    private lateinit var sceneView: ArSceneView
    private lateinit var placeButton: ExtendedFloatingActionButton
    private lateinit var modelNode: ArModelNode
    private lateinit var videoNode: VideoNode
    private lateinit var mediaPlayer: MediaPlayer

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

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.ad)

        placeButton = view.findViewById(R.id.place)

        placeButton.setOnClickListener {
            placeModel()
        }

        videoNode = VideoNode(
            sceneView.engine,
            scaleToUnits = 0.7f,
            centerOrigin = Position(y = -4f),
            glbFileLocation = "models/tree_branch.glb",
            player = mediaPlayer,
            onLoaded = { _, _ -> mediaPlayer.start() }
        )

        modelNode = ArModelNode(sceneView.engine, PlacementMode.INSTANT).apply {
            loadModelGlbAsync(
                glbFileLocation = "models/snowflake.glb",
                scaleToUnits = 1f,
                centerOrigin = Position(-0.5f)
            ) {
                sceneView.planeRenderer.isVisible = true
                //val materialInstance = it.materialInstances[0]
            }

            onAnchorChanged = {
                placeButton.isGone = it != null
            }
        }

        sceneView.addChild(modelNode)
        modelNode.addChild(videoNode)
    }

    private fun placeModel() {
        modelNode.anchor()
        sceneView.planeRenderer.isVisible = false
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
    }
}