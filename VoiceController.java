public class VoiceController implements Controller {
    private Receiver aMusicPlayer;
    public VoiceController(Receiver pMusicPlayer){
        aMusicPlayer=pMusicPlayer;
    }
    @Override
    public void next() {
        System.out.println("Command understood Playing next song in queue");
        aMusicPlayer.play(PLAY.DEFAULT);

    }
}