package seg3x02.converter

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WebController {

    @RequestMapping("/")
    fun home(model: Model): String {
        model.addAttribute("first", "")
        model.addAttribute("second", "")
        model.addAttribute("result", "")
        model.addAttribute("error", "")
        return "home"
    }

    @GetMapping("/calculate")
    fun calculate(
        @RequestParam(value = "first", required = false, defaultValue = "") first: String,
        @RequestParam(value = "second", required = false, defaultValue = "") second: String,
        @RequestParam(value = "operation", required = false, defaultValue = "") operation: String,
        model: Model
    ): String {

        model.addAttribute("first", first)
        model.addAttribute("second", second)

        val firstNumber = first.toDoubleOrNull()
        val secondNumber = second.toDoubleOrNull()

        if (firstNumber == null || secondNumber == null) {
            model.addAttribute("error", "Veuillez entrer deux nombres valides.")
            model.addAttribute("result", "")
            return "home"
        }

        if (operation == "/" && secondNumber == 0.0) {
            model.addAttribute("error", "Division par zéro impossible.")
            model.addAttribute("result", "")
            return "home"
        }

        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> {
                model.addAttribute("error", "Opération invalide.")
                model.addAttribute("result", "")
                return "home"
            }
        }

        model.addAttribute("result", String.format("%.2f", result))
        model.addAttribute("error", "")

        return "home"
    }
}