class BootStrap {

	def interchangeService

	def init = { servletContext ->
		println "Initialization ...."
		interchangeService.initialize()
	}

	def destroy = {
	}
}
