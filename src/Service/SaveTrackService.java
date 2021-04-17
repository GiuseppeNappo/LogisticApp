package Service;

import Entity.Track.Track;
import Entity.Track.TrackDao;

/**
 * Questa  classe offre un servizio  alle classi , in particolar modo alle classi che fungono da view , Questa classe prende in input nel costruttore un TrackDao;
 */
public class SaveTrackService {
    private TrackDao trackDao;

    public SaveTrackService(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    /**
     * Salva l'istanza dell'oggetto track nel db andando a richiamare l'opportuno metodo del track dao
     */
    public void saveTrack(Track track) {
        trackDao.save(track);
    }
}
