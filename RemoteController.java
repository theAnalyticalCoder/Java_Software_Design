public class RemoteController implements Controller {
    private Receiver aMusicPlayer;
    public RemoteController(Receiver pMusicPlayer){
        aMusicPlayer=pMusicPlayer;
    }
    @Override
    public void next() {
        aMusicPlayer.play(PLAY.DEFAULT);

    }
}
