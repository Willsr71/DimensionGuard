package net.willsr71.mystcraftutils;

import java.util.ArrayList;
import java.util.List;

public class DimensionData {
    private ArrayList<String> owners = new ArrayList<String>();
    private ArrayList<String> members = new ArrayList<String>();
    private String name;

    public DimensionData(String name, List<String> owners, List<String> members) {
        this.name = name;
        addOwners(owners);
        addMembers(members);
    }

    public void addOwners(List<String> players) {
        for (String player : players) {
            addOwner(player);
        }
    }

    public void addMembers(List<String> players) {
        for (String player : players) {
            addMember(player);
        }
    }

    public void addOwner(String player) {
        owners.add(player);
    }

    public void addMember(String player) {
        members.add(player);
    }

    public void removeOwner(String player) {
        owners.remove(player);
    }

    public void removeMember(String player) {
        members.remove(player);
    }

    public boolean isOwner(String player) {
        return owners.contains(player);
    }

    public boolean isMember(String player) {
        return members.contains(player);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getOwners() {
        return owners;
    }

    public ArrayList<String> getMembers() {
        return members;
    }
}
