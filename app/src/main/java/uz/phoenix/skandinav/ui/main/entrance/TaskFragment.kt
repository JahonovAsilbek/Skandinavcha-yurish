package uz.phoenix.skandinav.ui.main.entrance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import phoenix.skandinav.R
import phoenix.skandinav.databinding.FragmentTaskBinding
import uz.phoenix.skandinav.database.entities.MainPart
import uz.phoenix.skandinav.database.entities.Training

private const val ARG_PARAM1 = "training"
private const val ARG_PARAM2 = "task_position"
private const val ARG_PARAM3 = "main_part"

class TaskFragment : Fragment() {

    private var training: Training? = null
    private var mainPart: MainPart? = null
    private var taskPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            training = it.getSerializable(ARG_PARAM1) as Training?
            mainPart = it.getSerializable(ARG_PARAM3) as MainPart?
            taskPosition = it.getInt(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(layoutInflater)

        if (taskPosition == 3) {
            //update training object finished state
            if (training != null) {

            }

            if (mainPart != null) {

            }
        }

        if (training != null) {
            entrancePart()
        }

        if (mainPart != null) {
            mainpart()
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun mainpart() {
        when (taskPosition) {
            1 -> {
                binding.task.text = mainPart!!.task1Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        mainPart?.task1Video?.let { youTubePlayer.loadVideo(it, 0f) }
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
            2 -> {
                binding.task.text = mainPart!!.task2Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        mainPart?.task2Video?.let { youTubePlayer.loadVideo(it, 0f) }

                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
            3 -> {
                binding.task.text = mainPart!!.task3Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        mainPart?.task3Video?.let { youTubePlayer.loadVideo(it, 0f) }

                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
        }
    }

    private fun entrancePart() {
        when (taskPosition) {
            1 -> {
                binding.task.text = training!!.task1Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        if (training != null) {
                            training?.task1Video?.let { youTubePlayer.loadVideo(it, 0f) }
                        }
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
            2 -> {
                binding.task.text = training!!.task2Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        if (training != null) {
                            training?.task2Video?.let { youTubePlayer.loadVideo(it, 0f) }
                        }
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
            3 -> {
                binding.task.text = training!!.task3Text

                val youTubePlayer = binding.root.findViewById<YouTubePlayerView>(R.id.you_tube)
                lifecycle.addObserver(youTubePlayer)

                youTubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
                    override fun onApiChange(youTubePlayer: YouTubePlayer) {

                    }

                    override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {

                    }

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {

                    }

                    override fun onPlaybackQualityChange(
                        youTubePlayer: YouTubePlayer,
                        playbackQuality: PlayerConstants.PlaybackQuality
                    ) {

                    }

                    override fun onPlaybackRateChange(
                        youTubePlayer: YouTubePlayer,
                        playbackRate: PlayerConstants.PlaybackRate
                    ) {

                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        if (training != null) {
                            training?.task3Video?.let { youTubePlayer.loadVideo(it, 0f) }
                        }
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {

                    }

                    override fun onVideoDuration(
                        youTubePlayer: YouTubePlayer,
                        duration: Float
                    ) {

                    }

                    override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

                    }

                    override fun onVideoLoadedFraction(
                        youTubePlayer: YouTubePlayer,
                        loadedFraction: Float
                    ) {

                    }

                })
            }
        }
    }

}