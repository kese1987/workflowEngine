Creator "JGraphT GML Exporter - modified by Hayato Hess, Andreas Hofstadler"
Version 1
graph
[
	label ""
	directed 0
	node
	[
		id 1
		label "TASK3"
		graphics
		[
			type	"rectangle"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 2
		label "Gw"
		graphics
		[
			type	"diamond"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 3
		label "StartNode"
		graphics
		[
			type	"ellipse"
			fill	"#00ff00ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 4
		label "TASK4"
		graphics
		[
			type	"rectangle"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 5
		label "ErrorHandler"
		graphics
		[
			type	"triangle"
			fill	"#ffff00ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 6
		label "TASK1"
		graphics
		[
			type	"rectangle"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 7
		label "TASK2"
		graphics
		[
			type	"rectangle"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 8
		label "EndNode"
		graphics
		[
			type	"ellipse"
			fill	"#ff0000ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 9
		label "booleanGw"
		graphics
		[
			type	"diamond"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	node
	[
		id 10
		label "TASK5"
		graphics
		[
			type	"rectangle"
			fill	"#c0c0c0ff"
			line	"#000000ff"
		]
		LabelGraphics
		[
			fontStyle	"ITALIC"
		]
	]
	edge
	[
		id 11
		source 1
		target 8
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 12
		source 2
		target 1
		label "flow 0"
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 13
		source 2
		target 4
		label "flow 1"
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 14
		source 2
		target 10
		label "flow 2"
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 15
		source 3
		target 9
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 16
		source 4
		target 8
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 17
		source 5
		target 8
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 18
		source 6
		target 8
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 19
		source 7
		target 2
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 20
		source 9
		target 7
		label "false"
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 21
		source 9
		target 6
		label "true"
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
	edge
	[
		id 22
		source 10
		target 8
		label ""
		LabelGraphics
		[
			model	"centered"
			position	"center"
		]
		graphics
		[
			fill	"#000000ff"
			style	"DASHED"
			targetArrow	"short"
		]
	]
]
