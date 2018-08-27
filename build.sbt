enablePlugins(ScalaJSPlugin)

name := "scalajs-react-template"
version := "1.0"
scalaVersion := "2.12.6"


val scalaJSReactVersion = "1.2.3"
val scalaCssVersion = "0.5.5"
val reactJSVersion = "16.4.1"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.5",
  "com.github.japgolly.scalajs-react" %%% "core" % scalaJSReactVersion,
  "com.github.japgolly.scalajs-react" %%% "extra" % scalaJSReactVersion,
"com.github.japgolly.scalajs-react" %%% "ext-cats" % scalaJSReactVersion,
  "com.github.japgolly.scalacss" %%% "core" % scalaCssVersion,
  "com.github.japgolly.scalacss" %%% "ext-react" % scalaCssVersion
)


// creates single js resource file for easy integration in html page
skip in packageJSDependencies := false

// copy  javascript files to js folder,that are generated using fastOptJS/fullOptJS

crossTarget in (Compile, fullOptJS) := file("js")
crossTarget in (Compile, fastOptJS) := file("js")
crossTarget in (Compile, packageJSDependencies) := file("js")
crossTarget in (Compile, packageMinifiedJSDependencies) := file("js")
artifactPath in (Compile, fastOptJS) := ((crossTarget in (Compile, fastOptJS)).value /
  ((moduleName in fastOptJS).value + "-opt.js"))
scalacOptions += "-feature"

jsDependencies ++= Seq(

  "org.webjars.npm" % "react" % reactJSVersion
    /        "umd/react.development.js"
    minified "umd/react.production.min.js"
    commonJSName "React",

  "org.webjars.npm" % "react-dom" % reactJSVersion
    /         "umd/react-dom.development.js"
    minified  "umd/react-dom.production.min.js"
    dependsOn "umd/react.development.js"
    commonJSName "ReactDOM",

  "org.webjars.npm" % "react-dom" % reactJSVersion
    /         "umd/react-dom-server.browser.development.js"
    minified  "umd/react-dom-server.browser.production.min.js"
    dependsOn "umd/react-dom.development.js"
    commonJSName "ReactDOMServer")
enablePlugins(WorkbenchSplicePlugin)

ThisBuild / dependencyOverrides += "org.webjars.npm" % "js-tokens" % "3.0.2"

//workbenchDefaultRootObject := Some(("index.html", ""))  // (defaultRootObject, rootDirectory)

workbenchStartMode := WorkbenchStartModes.OnCompile

(scalaJSUseMainModuleInitializer in Compile) := true