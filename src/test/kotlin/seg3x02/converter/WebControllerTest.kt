package seg3x02.converter

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
class WebControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun request_to_home() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun addition() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "2")
                .param("second", "3")
                .param("operation", "+")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "5.00"))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun subtraction() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "10")
                .param("second", "4")
                .param("operation", "-")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "6.00"))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun multiplication() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "3")
                .param("second", "5")
                .param("operation", "*")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "15.00"))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun division() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "10")
                .param("second", "4")
                .param("operation", "/")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.model().attribute("result", "2.50"))
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun invalid_numbers() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "abc")
                .param("second", "3")
                .param("operation", "+")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.model()
                    .attribute("error", "Veuillez entrer deux nombres valides.")
            )
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }

    @Test
    fun division_by_zero() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/calculate")
                .param("first", "10")
                .param("second", "0")
                .param("operation", "/")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.model()
                    .attribute("error", "Division par zéro impossible.")
            )
            .andExpect(MockMvcResultMatchers.view().name("home"))
    }
}