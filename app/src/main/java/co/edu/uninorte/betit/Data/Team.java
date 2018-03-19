package co.edu.uninorte.betit.Data;


import java.io.Serializable;

import co.edu.uninorte.betit.R;

public enum Team implements Serializable {
    Russia(R.mipmap.russia_round_icon_640), Egypt(R.mipmap.egypt_640), Morocco(R.mipmap.morocco_640),
    Nigeria(R.mipmap.nigeria_640), Senegal(R.mipmap.senegal_640), Tunisia(R.mipmap.tunisia_640),
    Australia(R.mipmap.australia_640), Iran(R.mipmap.iran_round_icon_640), Japan(R.mipmap.japan_640),
    Korea(R.mipmap.korea_south_640  ), Arabia(R.mipmap.saudi_arabia_round_icon_640), Belgium(R.mipmap.belgium_640),
    Croatia(R.mipmap.croatia_640), Denmark(R.mipmap.denmark_640), England(R.mipmap.england_640), France(R.mipmap.france_640),
    Germany(R.mipmap.germany_640), Iceland(R.mipmap.iceland_640), Poland(R.mipmap.poland_640),
    Portugal(R.mipmap.portugal_640), Serbia(R.mipmap.serbia_640), Spain(R.mipmap.spain_640), Sweden(R.mipmap.sweden_640),
    Switzerland(R.mipmap.switzerland_640), CostaRica(R.mipmap.costa_rica_640), Mexico(R.mipmap.mexico_640),
    Panama(R.mipmap.panama_640),Argentina(R.mipmap.argentina_640), Brazil(R.mipmap.brazil_640),
    Colombia(R.mipmap.colombia_640), Peru(R.mipmap.peru_640), Uruguay(R.mipmap.uruguay_640);

    public int image;



    Team(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getImage() {
        return image;
    }
}

