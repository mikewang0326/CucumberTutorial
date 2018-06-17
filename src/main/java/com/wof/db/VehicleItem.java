package com.wof.db;

import com.wof.entity.VehicleEntity;
import com.wof.utils.TextUtils;

import java.sql.Date;


public class VehicleItem {
    /*
     * i.e KPC111
     */
    public String plate;

    /*
     * i.e passenger cars (type MA), passenger vehicles (MB), off-road vehicles (MC),
     * trailers (T)or any other special types of vehicle (O).
     */
    public String type;

    /*
     * i.e Toyota, Mazda, Ford, etc.
     */
    public String make;

    /*
     * i.e, Toyota: Crown, Camry, Prius, Corolla, Land Cruiser
     */
    public String model;


    public Date manufacture_date;

    /*
     * i.e diesel, petrol, electric, gas or other
     *
     *  Note: trailers (type T) do not have a fuel type
     */
    public String fuel_type;

    public int user_id;

    public boolean isValid() {
        boolean ret = false;
        if (!TextUtils.isEmpty(plate)
                && !TextUtils.isEmpty(type)
                && !TextUtils.isEmpty(make)
                && !TextUtils.isEmpty(model)
                && null != manufacture_date
                && !TextUtils.isEmpty(fuel_type)
                && user_id > 0) {
            ret = true;
        }

        return ret;
    }


    @Override
    public boolean equals(Object obj) {
        boolean ret = false;
        if (null != obj && obj instanceof VehicleItem) {
            VehicleItem outUserEntity = (VehicleItem) obj;
            if (outUserEntity.isValid() && outUserEntity.plate.equals(this.plate)
                    && outUserEntity.type.equals(this.type)
                    && outUserEntity.make.equals(this.make)
                    && outUserEntity.model.equals(this.model)
                    && outUserEntity.manufacture_date.equals(this.manufacture_date)
                    && outUserEntity.fuel_type.equals(this.fuel_type)
                    && outUserEntity.user_id == this.user_id
                    ) {
                ret = true;
            }
        }
        return ret;
    }


    public static VehicleEntity convertItemToEntity(VehicleItem vehicleItem) {
        VehicleEntity entity = new VehicleEntity();
        if (null != vehicleItem && vehicleItem.isValid()) {
            entity.plate = vehicleItem.plate;
            entity.type = vehicleItem.type;
            entity.make = vehicleItem.make;
            entity.model = vehicleItem.model;
            entity.manufacture_date = vehicleItem.manufacture_date;
            entity.fuel_type = vehicleItem.fuel_type;
            entity.user_id = vehicleItem.user_id;
        }

        return entity;
    }
}

