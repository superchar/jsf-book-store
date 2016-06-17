package com.dataart.booksapp.modules.general;

import java.util.function.Function;

/**
 * Created by vlobyntsev on 17.06.2016.
 */
public class GeneralMapper {

    public static <TModel, TViewModel> TModel loadModelFromViewModel(TViewModel viewModel, Function<TViewModel, Integer> idFetcher,
                                                                     Function<Integer, TModel> modelLoader) throws NotExistsException {
        Preconditions.throwIllegalArgumentIfParamIsNull(viewModel);
        Integer modelId = idFetcher.apply(viewModel);
        Preconditions.throwIllegalArgumentIfFalse(modelId > 0, String.format("Id of %s is not valid", viewModel.getClass().getName()));
        TModel targetModel = modelLoader.apply(modelId);
        Preconditions.throwNotExistsIfNull(targetModel);
        return targetModel;
    }
}
