
package universitylab.services;

import universitylab.datastructures.AVLTree;
import universitylab.model.Lab;
import universitylab.dao.LabDAO;

public class LabManager {

    private AVLTree labTree;
    private LabDAO labDAO;

    public LabManager() {
        labTree = new AVLTree();
        labDAO = new LabDAO();
    }

    // Add Lab
    public void addLab(Lab lab) {

        if (searchLab(lab.getLabId()) != null) {
            System.out.println("Lab ID already exists!");
            return;
        }

        try {
            labDAO.insertLab(lab);
            labTree.insertLab(lab);

            System.out.println("Lab added successfully!");

        } catch (Exception e) {
            System.out.println("Unable to add lab: " + e.getMessage());
        }
    }

    // Search Lab
    public Lab searchLab(String labId) {
        return labTree.searchLab(labId);
    }

    // Delete Lab
    public void deleteLab(String labId) {

        Lab lab = searchLab(labId);

        if (lab == null) {
            System.out.println("This lab does not exist!");
            return;
        }

        try {
            labDAO.deleteLab(labId);
            labTree.deleteLab(labId);

            System.out.println("Lab deleted successfully!");

        } catch (Exception e) {
            System.out.println("Unable to delete lab: " + e.getMessage());
        }
    }

    // Update Lab
    public void updateLab(String labName, String labId, String location) {

        Lab lab = searchLab(labId);

        if (lab == null) {
            System.out.println("This lab does not exist!");
            return;
        }

        lab.setLabName(labName);
        lab.setLocation(location);

        try {
            labDAO.updateLab(lab);
            System.out.println("Lab updated successfully!");

        } catch (Exception e) {
            System.out.println("Unable to update lab: " + e.getMessage());
        }
    }

    // Load Labs from Database
    public void loadLabs() {

        try {
            Lab[] labs = labDAO.getAllLabs();

            for (int i = 0; i < labs.length; i++) {

                if (searchLab(labs[i].getLabId()) == null) {
                    labTree.insertLab(labs[i]);
                }
            }


        } catch (Exception e) {
            System.out.println("Unable to load labs: " + e.getMessage());
        }
    }

    // Display All Labs
    public void displayAllLabs() {
        System.out.println("\n--- All Registered Labs ---");
        labTree.inorder();
    }

    // Total Labs
    public int getTotalLabs() {
        return labTree.getCount();
    }

    // Display Descending
    public void displayDescending() {
        labTree.displayDescending();
    }
}
