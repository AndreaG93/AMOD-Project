package gui;

import java.util.ArrayList;

/**
 * This class represents a generic graphical user interface.
 *
 * @author Andrea Graziani
 * @version 1.0
 */
public abstract class UserInterface {

    private ArrayList<UserInterface> userInterfaceObservers = new ArrayList<>();

    /**
     * This method is used to register a new {@code UserInterface} object.
     *
     * @param pObserver - Represents an {@link UserInterface} object.
     */
    public void addObserver(UserInterface pObserver) {
        this.userInterfaceObservers.add(pObserver);
    }

    /**
     * This method is used to remove a specified {@code UserInterface} object.
     *
     * @param pObserver - Represents an {@link UserInterface} object.
     */
    public void removeObserver(UserInterface pObserver) {
        this.userInterfaceObservers.remove(pObserver);
    }

    /**
     * This method is used to notify all observers.
     */
    public void notifyToObservers() {
        for (UserInterface pObserver : this.userInterfaceObservers) {
            pObserver.updateUserInterface();
        }
    }

    /**
     * This method is used to show current interface.
     */
    public abstract void showUserInterface();

    /**
     * This method is used to update an user interface.
     */
    public abstract void updateUserInterface();

    /**
     * This method is used to close current user interface.
     */
    public abstract void closeUserInterface();
}