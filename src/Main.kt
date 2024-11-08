/**
 * ------------------------------------------------------------------------
 * Doggy Game: Jailbreak
 * Level 3 programming project
 *
 * by Cameron Fitzsimmons
 *
 * escape prison before you are executed
 * ------------------------------------------------------------------------
 */


import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*

//=============================================================================================

/**
 *  Location Class
 */

class Location(val name: String, val description: String) {
    var north: Location? = null
    var east: Location? = null
    var south: Location? = null
    var west: Location? = null
    var item: Item? = null
    var key: Item? = null
    var blocked: Item? = null


    fun addNorth(location: Location) {
        if (north == null) {
            north = location
            location.addSouth(this)
        }
    }

    fun addEast(location: Location) {
        if (east == null) {
            east = location
            location.addWest(this)
        }
    }

    fun addWest(location: Location) {
        if (west == null) {
            west = location
            location.addEast(this)
        }
    }

    fun addSouth(location: Location) {
        if (south == null) {
            south = location
            location.addNorth(this)
        }
    }

    fun addItem(newItem: Item) {
        item = newItem
    }

    fun removeItem() {
        item = null
    }

    fun addKey(roomkey: Item) {
        key = roomkey
    }

    fun removeKey() {
        key = null
    }

    fun addBlock(block: Item){
        blocked = block
    }

    fun removeBlock(){
        blocked = null
    }

}

/**
 * item class
 */
class Item(val name: String, val description: String){
    override fun toString(): String{
        return "$name: $description"
    }
}

/**
 * GUI class
 * Defines the UI and responds to events
 */
class GUI : JFrame(), ActionListener {

    // Setup list of Locations
    val locations = mutableListOf<Location>()
    var currentLocation: Location

    // Setup inventory
    val inventory = DefaultListModel<Item>()

    // Setup timer
    var timer = 120
    private lateinit var timerTitle: JLabel



    // Setup some properties to hold the UI elements
    private lateinit var locationLabel: JLabel
    private lateinit var locationDesc: JLabel
    private lateinit var searchResult: JLabel

    // Set up the movement buttons
    private lateinit var northButton: JButton
    private lateinit var eastButton: JButton
    private lateinit var westButton: JButton
    private lateinit var southButton: JButton

    //Set up actions
    private lateinit var searchButton: JButton
    private lateinit var actionButton: JButton

    //Set up inventory
    private var inventoryWindow =InventoryWindow(inventory)
    private lateinit var inventoryButton: JButton


    /**
     * Create, build and run the UI
     */

    init {
        setupWindow()
        setUpMap()
        buildMainUI()

        currentLocation = locations.first()

        showLocation()
        // Show the app, centred on screen
        setLocationRelativeTo(null)
        isVisible = true
        countDownTimer()
        }

        private fun setUpMap(){
            //Creates Locations and adds to list
            val cell = Location("Prison Cell", "A small room with sleek metal walls. There is a small bed on the wall and a toilet in the corner. A large metal door with a small slit leads to a hallway.")
            val hallway = Location("Hallway", "A long hallway with white sleek metal walls. Prison cells line the walls and there are doors at both ends.")
            val recreation = Location("Recreation Room", "A large room with white metal walls. A large sofa is in the middle of the room with a TV infront of it. A pool table is tucked into the corner.")
            val intersection = Location("Intersection", "A intersection in the hallway connecting the rows of cells to the main hallway. An unconscious guard is laying on the floor.")
            val security = Location("Security Office", "A small room full on monitors and buttons. The monitors are connected to security cameras that show the entire prison. You watch on the monitors as the rest of the Prison is in chaos.")
            val lose = Location("", "")
            val hallway2 = Location("Hallway", "A long hallway with bloody metal walls.Torn posters preaching unity and cooperation of all mankind line the walls. There is a stench of decay.")
            val hallway3 = Location("Hallway", "A Short hallway branching off two different ways. Blood splatters the walls and you see a mangled and torn pile of meat and armour in the corner.")
            val hallway4 = Location("Hallway", "A long hallway with a branch off to the side. Muddy Bootmarks all lead from around the corner, all heading in the same direction.")
            val destroyway = Location("Destroyed Room", "A small room half covered in debris. The roof is caved in and a small fire is going in the corner. You can see the remains of a guard that was crushed under the rubble.")
            val hallway5 = Location("Hallway", "A long hallway covered in dirt and muck. The floor is covered in muddy bootprints all facing one direction.")
            val hallway6 = Location("Hallway", "A short hallway leading to a single steal door.")
            val armoury = Location("Armoury", "A large armoury filled with lockers and benches. Most of the lockers are empty and the whole place is in disarray")

            locations.add(cell)
            locations.add(hallway)
            locations.add(recreation)
            locations.add(intersection)
            locations.add(security)
            locations.add(hallway2)
            locations.add(hallway3)
            locations.add(hallway4)
            locations.add(destroyway)
            locations.add(hallway5)
            locations.add(hallway6)
            locations.add(armoury)
            locations.add(lose)

            // connects all the locations
            hallway6.addWest(armoury)
            hallway5.addWest(hallway6)
            hallway4.addWest(hallway5)
            hallway2.addWest(destroyway)
            hallway3.addNorth(security)
            hallway3.addWest(hallway2)
            intersection.addNorth(hallway3)
            intersection.addSouth(hallway4)
            cell.addNorth(hallway)
            hallway.addEast(recreation)
            hallway.addWest(intersection)

            // Creates items
            val cue = Item("Pool Cue", "A wood pool cue.")
            val guard = Item("Prison Guard", "")
            val red = Item("Red Card", "A red key card.")
            val blue = Item("Blue Card", "A blue key card.")
            val redDoor = Item("Red Security Door","")
            val blueDoor = Item("Blue Security Door","")

            // puts the items in locations
            recreation.addItem(cue)
            intersection.addItem(red)
            destroyway.addItem(blue)

            //adds 'blocks' and 'keys'
            hallway6.addBlock(blueDoor)
            hallway6.addKey(blue)
            intersection.addBlock(guard)
            intersection.addKey(cue)
            security.addBlock(redDoor)
            security.addKey(red)
    }

    /**
     * Configure the main window
     */
    private fun setupWindow() {
        title = "Doggy Game: Jailbreak"
        contentPane.preferredSize = Dimension(1000, 1000)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI
     */
    private fun buildMainUI() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 30)

        locationLabel = JLabel("LOCATION NAME GOES HERE", SwingConstants.CENTER)
        locationLabel.bounds = Rectangle(250, 100, 500, 42)
        locationLabel.font = baseFont
        add(locationLabel)

        locationDesc = JLabel("LOCATION DESC GOES HERE", SwingConstants.CENTER)
        locationDesc.bounds = Rectangle(100, 429, 800, 141)
        locationDesc.font = baseFont
        add(locationDesc)

        timerTitle = JLabel("Seconds until lockdown activates: $timer", SwingConstants.CENTER)
        timerTitle.bounds = Rectangle(247, 290, 507, 82)
        timerTitle.font = baseFont
        add(timerTitle)

        searchResult = JLabel("", SwingConstants.CENTER)
        searchResult.bounds = Rectangle(232, 550, 537, 141)
        searchResult.font = baseFont
        add(searchResult)

        northButton = JButton("North")
        northButton.bounds = Rectangle(380,687,240,40)
        northButton.font = baseFont
        northButton.addActionListener(this)
        add(northButton)

        eastButton = JButton("East")
        eastButton.bounds = Rectangle(620,728,240,40)
        eastButton.font = baseFont
        eastButton.addActionListener(this)
        add(eastButton)

        westButton = JButton("West")
        westButton.bounds = Rectangle(140,728,240,40)
        westButton.font = baseFont
        westButton.addActionListener(this)
        add(westButton)

        southButton = JButton("South")
        southButton.bounds = Rectangle(380,769,240,40)
        southButton.font = baseFont
        southButton.addActionListener(this)
        add(southButton)

        inventoryButton = JButton("Inventory")
        inventoryButton.bounds = Rectangle(640,877,200,40)
        inventoryButton.font = baseFont
        inventoryButton.addActionListener(this)
        add(inventoryButton)

        searchButton = JButton("search")
        searchButton.bounds = Rectangle(160,877,200,40)
        searchButton.font = baseFont
        searchButton.addActionListener(this)
        add(searchButton)

        actionButton = JButton("action")
        actionButton.bounds = Rectangle(400,877,200,40)
        actionButton.font = baseFont
        actionButton.addActionListener(this)
        add(actionButton)
    }


    private fun showLocation(){
        locationLabel.text = currentLocation.name
        locationDesc.text = "<html>${currentLocation.description}</html>"
        searchResult.text = ""

        checkNorth()
        checkSouth()
        checkEast()
        checkWest()
    }

    /**
     * Handle any UI events
     */

    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            northButton -> walkNorth()
            southButton -> walkSouth()
            eastButton -> walkEast()
            westButton -> walkWest()
            inventoryButton -> showInventory()
            searchButton -> searchRoom()
            actionButton -> useItem()
        }
    }

    /**
     *  Count down timer
     */

    private fun countDownTimer() {
        while (timer >= 0) {
            timer = timer - 1
            Thread.sleep(1000)
            timerTitle.text = "Seconds until lockdown activates: $timer"

            if (timer <= 0) {
                timerTitle.text = "<html>Lockdown has been activated, your fate is sealed.</html>"
                currentLocation = locations.last()
                showLocation()
            }
        }
    }


    /**
     * Check if a room is connected
     */

    private fun checkNorth(){
        if(currentLocation.north != null) {
            northButton.isEnabled = true
        }
        else {
            northButton.isEnabled = false
        }
    }

    private fun checkSouth(){
        if(currentLocation.south != null) {
            southButton.isEnabled = true
        }
        else {
            southButton.isEnabled = false
        }
    }

    private fun checkEast(){
        if(currentLocation.east != null) {
            eastButton.isEnabled = true
        }
        else {
            eastButton.isEnabled = false
        }
    }

    private fun checkWest(){
        if(currentLocation.west != null) {
            westButton.isEnabled = true
        }
        else {
            westButton.isEnabled = false
        }
    }

    /**
     * Move to location
     */

    private fun walkNorth() {
        if (currentLocation.north!!.blocked != null){
            searchResult.text = "You are blocked by ${currentLocation.north!!.blocked}!"
        }
        else {
            currentLocation = currentLocation.north!!
            showLocation()
        }
    }

    private fun walkEast() {
        if (currentLocation.east!!.blocked != null){
            searchResult.text = "You are blocked by ${currentLocation.east!!.blocked}!"
        }
        else {
            currentLocation = currentLocation.east!!
            showLocation()
        }
    }

    private fun walkWest() {
        if (currentLocation.west!!.blocked != null){
            searchResult.text = "You are blocked by ${currentLocation.west!!.blocked}!"
        }
        else {
            currentLocation = currentLocation.west!!
            showLocation()
        }
    }

    private fun walkSouth() {
        if (currentLocation.south!!.blocked != null){
            searchResult.text = "You are blocked by ${currentLocation.south!!.blocked}!"
        }
        else {
            currentLocation = currentLocation.south!!
            showLocation()
        }
    }

    /**
     * actions
     */

    private fun showInventory() {
        inventoryWindow.setLocationRelativeTo(null)
        inventoryWindow.isVisible = true
    }

    private fun useItem() {
        useWest()
    }

    /**
     * Check directions for use
     */
    private fun useWest() {
        if (inventory.contains(currentLocation.west?.key)) {
            searchResult.text = "You used ${currentLocation.west!!.key!!.name}!"
            currentLocation.west!!.removeKey()
            currentLocation.west!!.removeBlock()
        }
        else {
            useEast()
        }
    }

    private fun useEast() {
        if (inventory.contains(currentLocation.east?.key)) {
            searchResult.text = "You used ${currentLocation.east!!.key!!.name}!"
            currentLocation.east!!.removeKey()
            currentLocation.east!!.removeBlock()
        }
        else {
            useSouth()
        }
    }

    private fun useSouth() {
        if (inventory.contains(currentLocation.south?.key)) {
            searchResult.text = "You used ${currentLocation.south!!.key!!.name}!"
            currentLocation.south!!.removeKey()
            currentLocation.south!!.removeBlock()
        }
        else {
            useNorth()
        }
    }

    private fun useNorth() {
        if (inventory.contains(currentLocation.north?.key)) {
            searchResult.text = "You used ${currentLocation.north!!.key!!.name}!"
            currentLocation.north!!.removeKey()
            currentLocation.north!!.removeBlock()
        }
        else {
            searchResult.text = "There is nothing you can use here."
        }
    }

    private fun searchRoom() {
        if (currentLocation.item != null) {
            searchResult.text = "You found a ${currentLocation.item!!.name}!"
            inventory.addElement(currentLocation.item!!)
            currentLocation.removeItem()
        }
        else {
            searchResult.text = "This room is empty!"
        }
    }

}


//=============================================================================================

class InventoryWindow(val inventory: DefaultListModel<Item>) : JDialog() {


    private lateinit var inventoryLabel: JLabel
    private lateinit var inventoryDesc: JLabel
    private lateinit var backButton: JButton
    private lateinit var inventoryList: JList<Item>


    init {
        setupWindow()
        buildInvUI()
    }

    private fun setupWindow() {
        title = "Inventory"
        contentPane.preferredSize = Dimension(500, 500)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    private fun buildInvUI() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 30)

        inventoryLabel = JLabel("Inventory: ", SwingConstants.CENTER)
        inventoryLabel.bounds = Rectangle(174, 50, 152, 42)
        inventoryLabel.font = baseFont
        add(inventoryLabel)

        inventoryList = JList(inventory)
        inventoryList.bounds = Rectangle(45, 106, 400, 357)
        inventoryList.font = baseFont
        add(inventoryList)

        backButton = JButton("Back")
        backButton.bounds = Rectangle(640,877,200,40)
        backButton.font = baseFont
        add(backButton)
    }

//    private fun loadList(){
//        inventoryList.text =
//    }
}

//============================================================================

/**
 * Launch the application
 */
fun main() {
    // Flat, Dark look-and-feel
    FlatDarkLaf.setup()

    // Create the UI
    GUI()
}


