package it.ecosw.dudo.settings;

/**
 * This file is part of Dudo for Android software.
 *
 *  Dudo is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Dudo is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Dudo.  If not, see <http://www.gnu.org/licenses/>.
 */

import it.ecosw.dudo.R;
import it.ecosw.dudo.games.PlayerInfo;
import it.ecosw.dudo.media.BackgroundStatus;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

/**
 * Helper for interfacing with settings options
 * @author Enrico Strocchi
 *
 */
public class SettingsHelper {
	
	private Activity mContext;
	
	public static final String PLAYERNAME_SETTING = "playername_setting";
	
	public static final String PLAYERNUM_SETTING = "playernum_setting";
	
	public static final String PLAYERSAVE_SETTING = "playersave_setting";

	public static final String SOUND_SETTING = "sound_setting";
	
	public static final String ANIMATION_SETTING = "animation_setting";
	
	public static final String SORTING_SETTING = "sorting_setting";
	
	public static final String VIBRATION_SETTING = "vibration_setting";
	
	public static final String STYLE_SETTING = "style_setting";
	
	public static final String BACKGROUNDTYPE_SETTING = "backgroundtype_setting";
	public static final String BACKGROUND_SOLIDCOLOR_SETTING = "background_solidcolor_setting";
	public static final String BACKGROUD_TEXTCOLOR_SETTING = "background_textcolor_setting";
	public static final String BACKGROUND_IMAGE_SETTING = "background_image_setting";
	
	public static final String LASTVERSIONRUN_SETTING = "lastversionrun_setting";
	
	public static final String CHRONO_SETTING = "chrono_setting";
	
	/**
	 * Constructor
	 * @param context
	 */
	public SettingsHelper(Activity context) {
		mContext = context;
	}
	
	/**
	 * Return true if sound are activated
	 * @return true if sound activated
	 */
	public boolean isSoundActivated(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(SOUND_SETTING, true);
	}
	
	/**
	 * return true if animation are activated
	 * @return true if animation activated
	 */
	public boolean isAnimationActivated(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(ANIMATION_SETTING, true);
	}
	
	/**
	 * Return true if sorting is activated
	 * @return true if sorting activated
	 */
	public boolean isSortingActivated(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(SORTING_SETTING, false);
	}
	
	/**
	 * Return true if vibration is activated
	 * @return true if vibration is activated
	 */
	public boolean isVibrationActivated(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(VIBRATION_SETTING, true);
	}
	
	/**
	 * Return the style for die
	 * @return description of style for dice
	 */
	public String getStyle(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getString(STYLE_SETTING, "CLASSIC");
	}
	
	/**
	 * Return the background status
	 * @return background status
	 */
	public BackgroundStatus getBackgroundStatus(){
		boolean isimage = PreferenceManager.getDefaultSharedPreferences(mContext).getBoolean(BACKGROUNDTYPE_SETTING, true);
		int textcolor = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(BACKGROUD_TEXTCOLOR_SETTING,Color.BLACK);
		if(!isimage) {
			int backcolor = PreferenceManager.getDefaultSharedPreferences(mContext).getInt(BACKGROUND_SOLIDCOLOR_SETTING,0);
			return new BackgroundStatus(backcolor,textcolor);
		} else {
			String image = PreferenceManager.getDefaultSharedPreferences(mContext).getString(BACKGROUND_IMAGE_SETTING, "GREENCARPET");
			return new BackgroundStatus(image,textcolor);
		}
	}
	
	/**
	 * Return the number of players of last match
	 * @return number of players (1 to 6)
	 */
	public int getNumPlayers(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getInt(PLAYERNUM_SETTING, 2);
	}
	
	/**
	 * Save the number of players
	 * @param num number of players
	 * @return true if write was correct
	 */
	public boolean setNumPlayers(int num){
		SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
		spe.putInt(PLAYERNUM_SETTING, num);
		spe.commit();
		return true;
	}
	
	/**
	 * Return the information about one player
	 * @param num Player Number (0 to 5)
	 * @return Player info
	 */
	public PlayerInfo getPlayerStatus(int num){
		String name = PreferenceManager.getDefaultSharedPreferences(mContext).getString(PLAYERNAME_SETTING+num,mContext.getText(R.string.text_player).toString());
		String saved = PreferenceManager.getDefaultSharedPreferences(mContext).getString(PLAYERSAVE_SETTING+num,"00000");
		return new PlayerInfo(name, saved);
	}
	
	/**
	 * Save information about one player
	 * @param num Player Number (0 to 5)
	 * @param info Player Info
	 * @return 0 if write successfully
	 */
	public int setPlayerStatus(int num, PlayerInfo info){
		SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
		spe.putString(PLAYERNAME_SETTING+num, info.getName());
		spe.putString(PLAYERSAVE_SETTING+num, info.getSave());
		spe.commit();
		return 0;
	}
	
	/**
	 * Return last version of software was run
	 * @return last version of software
	 */
	public String getLastVersionRun(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getString(LASTVERSIONRUN_SETTING, "0");
	}
	
	/**
	 * Save the version of software
	 * @param version software version
	 * @return 0 if writing was correct
	 */
	public int setLastVersionRun(String version){
		SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
		spe.putString(LASTVERSIONRUN_SETTING, version);
		spe.commit();
		return 0;
	}
	
	/**
	 * Return last chrono save
	 * @return last chrono save
	 */
	public long getChronoTime(){
		return PreferenceManager.getDefaultSharedPreferences(mContext).getLong(CHRONO_SETTING,0);
	}
	
	/**
	 * Save chrono time
	 * @param version software version
	 * @return 0 if writing was correct
	 */
	public int setChronoTime(Long time){
		SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
		spe.putLong(CHRONO_SETTING, time);
		spe.commit();
		return 0;
	}

}
