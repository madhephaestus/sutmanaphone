File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/madhephaestus/sutmanaphone.git",//git repo URL
		"master",//branch
		"shape.svg.SVG"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())

HashMap<String,List<Polygon>> polygonsByLayer = s.toPolygons()
Polygon myProfile = polygonsByLayer.get("Slice 1").get(0)

print myProfile