#ifndef CAFFECLASSIFICATION_H
#define CAFFECLASSIFICATION_H

#ifndef CPU_ONLY
#define CPU_ONLY
#include <caffe/caffe.hpp>
#else
#include <caffe/caffe.hpp>
#endif

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

#include <algorithm>
#include <iosfwd>
#include <memory>
#include <string>
#include <utility>
#include <vector>


using namespace caffe;  // NOLINT(build/namespaces)
using std::string;

/* Pair (label, confidence) representing a prediction. */
typedef std::pair<string, float> Prediction;

class CaffeClassification {

  private:
    shared_ptr<Net<float> > net_;
    cv::Size input_geometry_;
    int num_channels_;
    cv::Mat mean_;
    std::vector<string> labels_;

  public:
    CaffeClassification(const string&, const string&, const string&, const string&);

    std::vector<Prediction> Classify(const cv::Mat&, int N = 5);

  private:
    void SetMean(const string&);

    std::vector<float> Predict(const cv::Mat&);

    void WrapInputLayer(std::vector<cv::Mat>*);

    void Preprocess(const cv::Mat&, std::vector<cv::Mat>*);


};

#endif // CAFFECLASSIFICATION_H
