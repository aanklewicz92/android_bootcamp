package com.droidonroids.weatherbootcamp.helpers.image;

import com.droidonroids.weatherbootcamp.data.network.consts.Const;

public class AddressBuilder {
	public static String getImageAddress(String iconName) {
		return Const.ICON_ADDRESS + iconName + Const.ICON_FORMAT;
	}
}
