package net.willsr71.mystcraftutils;

import java.util.ArrayList;

public class DimensionData {
    private ArrayList<String> owners = new ArrayList<>();
    private ArrayList<String> members = new ArrayList<>();
    public String name;
    public boolean claimed;

    public void addOwner(String player){
        owners.add(player);
    }

    public void addMember(String player){
        members.add(player);
    }

    public void removeOwner(String player){
        owners.remove(player);
    }

    public void removeMember(String player){
        members.remove(player);
    }

    public boolean isOwner(String player){
        return owners.contains(player);
    }

    public boolean isMember(String player){
        return members.contains(player);
    }

    public ArrayList<String> getOwners(){
        return owners;
    }

    public ArrayList<String> getMembers(){
        return members;
    }
}