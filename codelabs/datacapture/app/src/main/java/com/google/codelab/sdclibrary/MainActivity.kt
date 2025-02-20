/*
 * Copyright 2022-2024 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codelab.sdclibrary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import com.google.android.fhir.datacapture.QuestionnaireFragment
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    var questionnaireJsonString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 4.2 Replace with code from the codelab to add a questionnaire fragment.
        // Step 2: Configure a QuestionnaireFragment
        questionnaireJsonString = getStringFromAssets("questionnaire.json")

        val questionnaireFragment =
            QuestionnaireFragment.builder().setQuestionnaire(questionnaireJsonString!!).build()

        // Step 3: Add the QuestionnaireFragment to the FragmentContainerView
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, questionnaireFragment)
            }
        }
// Submit button callback
        supportFragmentManager.setFragmentResultListener(
            QuestionnaireFragment.SUBMIT_REQUEST_KEY,
            this,
        ) { _, _ ->
            submitQuestionnaire()
        }

    }

    private fun submitQuestionnaire() =
        lifecycleScope.launch {
            // 5 Replace with code from the codelab to get a questionnaire response.
            // Get a questionnaire response
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    as QuestionnaireFragment
            val questionnaireResponse = fragment.getQuestionnaireResponse()

            // Print the response to the log
            val jsonParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()
            val questionnaireResponseString =
                jsonParser.encodeResourceToString(questionnaireResponse)
            Log.d("response", questionnaireResponseString)

            // 6 Replace with code from the codelab to extract FHIR resources from QuestionnaireResponse.
        }

    private fun getStringFromAssets(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }

}