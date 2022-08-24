package com.example.sheapp.HairCare;

public class ModelHair {
    String Maskimage, MaskName, MaskDescription;

    ModelHair(){}

    public ModelHair(String maskimage, String maskName, String maskDescription) {
        Maskimage = maskimage;
        MaskName = maskName;
        MaskDescription = maskDescription;
    }

    public String getMaskimage() {
        return Maskimage;
    }

    public void setMaskimage(String maskimage) {
        Maskimage = maskimage;
    }

    public String getMaskName() {
        return MaskName;
    }

    public void setMaskName(String maskName) {
        MaskName = maskName;
    }

    public String getMaskDescription() {
        return MaskDescription;
    }

    public void setMaskDescription(String maskDescription) {
        MaskDescription = maskDescription;
    }
}
