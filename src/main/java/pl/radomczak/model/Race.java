package pl.radomczak.model;

import pl.radomczak.model.exception.NoSuchRaceException;

public enum Race {
    RYCERZ, LORD_DEMONOW, NEKROMANTA, LOWCA, CZARODZIEJ, CZARNOKSIEZNIK, BARBARZYNCA, KAPLAN_RUNOW;

    public static Race createOptionFromString(String race) throws NoSuchRaceException {
        try {
            return Race.valueOf(race);
        }catch (IllegalArgumentException e) {
            throw new NoSuchRaceException("Nie ma takiej rasy o nazwie " + race);
        }
    }
}
