//var assert = require('assert');
//var locations = require('../../controllers/locations/locations');
//var http_mocks = require('node-mocks-http');
var should = require('should');
var needle 		= require('needle');

describe('Location CREATE function: ', function() {
  it('Should add new Locations: ', function(done)
  {
	var locationData = {
		"location_type" : "mochaTest",
		"room" : "testRoom",
		"building" : "testBuilding",
		"lat" : -23.762481,
		"lng" : 28.293426,
		"level" : 4,
		"ground" : 2
	};

	/*var batLocationData = {

		"location_type" : "mochaTest",
		"room" : "testRoom",
		"building" : "testBuilding",
		"lat" : -23.762481,
		"lng" : 28.293426,
		"level" : 4,
		"ground" : 2

		"location_type" : "mochaTest2",
		"room" : "testRoom2",
		"building" : "testBuilding2",
		"lat" : -23.762491,
		"lng" : 28.293436,
		"level" : 5,
		"ground" : 3

		"location_type" : "mochaTest3",
		"room" : "testRoom3",
		"building" : "testBuilding3",
		"lat" : -23.762471,
		"lng" : 28.2934726,
		"level" : 6,
		"ground" : 4
	}*/
	
	needle.post('localhost:3000/location', locationData, function(err, res) //for batch insert use the batLocationData varieble for batch insert.
	{
		if (err) { done(); return console.error(err.message); }
		
		res.statusCode.should.equal(200)
		res.body.should.have.property('data');
		
		// to display body content
		//console.log(res.body.data);
		
		done();
	});
	
  });
});
