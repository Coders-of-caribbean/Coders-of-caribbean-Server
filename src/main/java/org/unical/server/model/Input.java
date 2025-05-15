package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Map;


/*
* STRUCTURE:
*
* {
	thommardo: {
		x: 12,
		y: 13,
		speed: 1,
		rum: 90,
		direction: 6,
		enemies:[
			tung:{
				x: 10
				y: 20
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			},
			cucinino:{
				x: 3
				y: 7
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			}],
		bombs:[{x1,y1,dmg}, {x2,y2,dmg},...],
		barrels:[{x1,y1,qty}, {x2,y2,qty}]
	},

	tung: {
		x: 12,
		y: 13,
		speed: 1,
		rum: 90,
		direction: 6,
		enemies:[
			thommardo:{
				x: 10
				y: 20
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			},
			cucinino:{
				x: 3
				y: 7
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			}],
		bombs:[{x1,y1,dmg}, {x2,y2,dmg},...],
		barrels:[{x1,y1,qty}, {x2,y2,qty}]
	},

	cucinino: {
		x: 12,
		y: 13,
		speed: 1,
		rum: 90,
		direction: 6,
		enemies:[
			tung:{
				x: 10
				y: 20
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			},
			thommardo:{
				x: 3
				y: 7
				speed: <-- posso sapere la velocità delle barche avversarie??
				rum: <-- posso sapere il rum delle barche avversarie??
				direction: <-- posso sapere la direction delle barche avversarie??
			}],
		bombs:[{x1,y1,dmg}, {x2,y2,dmg},...],
		barrels:[{x1,y1,qty}, {x2,y2,qty}]
	},

}
* */

@Data
@NoArgsConstructor
public class Input {
    private Map<String, PlayerData> input;
}
