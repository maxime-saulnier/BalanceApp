package com.example.balanceapp.dto

class UserBodyDTO constructor(weight: Double, IMC: Double, fatMass: Double, muscleMass: Double, waterMass: Double, BPM: Double){
        private var weight: Double? =weight;
        private var IMC: Double? = IMC;
        private var fatMass: Double? =  fatMass;
        private var muscleMass: Double? =  muscleMass;
        private var waterMass: Double? =  waterMass;
        private var BPM: Double? = BPM;
        //private var pwv: Double? =  56.0;
        fun getweight(): Double?
        {
            return (this.weight)
        }
        fun setweight(weight: Double)
        {
            this.weight=weight
        }
        fun getIMC(): Double?
        {
            return (this.IMC)
        }
        fun setIMC(IMC: Double)
        {
            this.IMC=IMC
        }
        fun getfatMass(): Double?
        {
            return (this.fatMass)
        }
        fun setfatMass(fatMass: Double)
        {
            this.fatMass=fatMass
        }
        fun getmuscleMass(): Double?
        {
            return (this.muscleMass)
        }
        fun setmuscleMass(muscleMass: Double)
        {
            this.muscleMass=muscleMass
        }
        fun getwaterMass(): Double?
        {
            return (this.waterMass)
        }
        fun setwaterMass(waterMass: Double)
        {
            this.waterMass=waterMass
        }
        fun getBPM(): Double?
        {
            return (this.BPM)
        }
        fun setBPM(BPM: Double)
        {
            this.BPM=BPM
        }
    /*
        fun getpwv(): Double?
        {
            return (this.pwv)
        }
        fun setpwv(pwv: Double)
        {
            this.pwv=pwv
        }
    */
}