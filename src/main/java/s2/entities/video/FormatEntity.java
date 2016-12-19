package s2.entities.video;

import s2.entities.common.BasicEntity;

import javax.persistence.*;

/**
 * Created by russl on 12/14/2016.
 */
//@Entity
//@Table(name = "video_format")
public class FormatEntity extends BasicEntity {



    String format_tip;
    String format_desc;
    String format_cd;

    String format_ref;


    // https://en.wikipedia.org/wiki/List_of_television_formats_and_genres
    //

    /*
    clip
     docufiction
     documentary
     single (one-time) episode
     made-for-TV film
     franchise
     mini-episode
     miniseries
     micro-series
     mockumentary
     pilot
     prequel
     reboot
     remake
     segment
     sequel
     serial
     series




     */

    /*
    animated:
    anime
    computer animation (CGI)
    stop-motion
    traditional animation

    live-action

    models

    puppetry

     */
}
